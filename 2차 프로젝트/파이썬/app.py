from flask import Flask, jsonify, render_template
import cx_Oracle
import nltk
from flask_cors import CORS
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

app = Flask(__name__)
# CORS(app, resources={r"/get_recommendations": {"origins": "http://localhost:63342"}})

nltk.download('punkt')
nltk.download('stopwords')

def run_recommendation_algorithm():
    # DB 연결 및 쿼리 수행 코드
    dsn = cx_Oracle.makedsn('192.168.30.28', '1521', 'xe')
    connection = cx_Oracle.connect(user='mbti', password='1111', dsn=dsn)

    cursor = connection.cursor()
    query = "SELECT 국문영화명, 영화정보, 영화장르, url FROM movie"  # "영화장르" 컬럼명 사용
    query2 = "SELECT name FROM moviedata"
    cursor.execute(query)
    movie_data = cursor.fetchall()

    cursor.execute(query2)
    query2_result = cursor.fetchall()

    cursor.close()
    connection.close()

    all_movie_names = [row[0] for row in query2_result]

    if not all_movie_names:
        return "영화 이름을 가져올 수 없습니다."

    vectorizer = TfidfVectorizer()
    movie_texts = [row[1] for row in movie_data]
    tfidf_matrix = vectorizer.fit_transform(movie_texts)
    cosine_similarities = cosine_similarity(tfidf_matrix, tfidf_matrix)

    result = {}

    for target_movie_name in all_movie_names:
        target_movie_idx = None
        for idx, movie_row in enumerate(movie_data):
            if movie_row[0] == target_movie_name:
                target_movie_idx = idx
                break

        if target_movie_idx is None:
            result[target_movie_name] = f"영화 '{target_movie_name}'을(를) 찾을 수 없습니다."
            continue

        similar_movies = [(idx, similarity) for idx, similarity in enumerate(cosine_similarities[target_movie_idx]) if
                          similarity >= 0.05]
        similar_movies = sorted(similar_movies, key=lambda x: x[1], reverse=True)

        similar_movie_list = []
        for idx, similarity_score in similar_movies:
            if idx != target_movie_idx:
                similar_movie_name = movie_data[idx][0]
                similar_movie_info = movie_data[idx][1]
                similar_movie_genre = movie_data[idx][2]
                similar_movie_url = movie_data[idx][3]
                similar_movie_list.append((similar_movie_name, similarity_score, similar_movie_genre, similar_movie_url,similar_movie_info))

        result[target_movie_name] = similar_movie_list

    return result


@app.route('/get_recommendations', methods=['GET'])
def get_recommendations():
    result = run_recommendation_algorithm()
    if isinstance(result, str):
        return jsonify(error=result)

    return jsonify(recommendations=result)

if __name__ == '__main__':
    app.run(debug=True)
