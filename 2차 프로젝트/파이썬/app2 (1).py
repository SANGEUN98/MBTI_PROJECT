from flask import Flask, jsonify
import cx_Oracle
import nltk
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

app = Flask(__name__)

nltk.download('punkt')
nltk.download('stopwords')

def run_recommendation_algorithm():
    # DB 연결 및 쿼리 수행 코드
    dsn = cx_Oracle.makedsn('192.168.30.28', '1521', 'xe')
    connection = cx_Oracle.connect(user='mbti', password='1111', dsn=dsn)

    cursor = connection.cursor()
    query = "SELECT 제목, 이미지, 작가, genre, 서술 FROM book"
    query2 = "SELECT title FROM bookdata"
    cursor.execute(query)
    book_data = cursor.fetchall()

    cursor.execute(query2)
    query2_result = cursor.fetchall()
    print(query2_result)

    cursor.close()
    connection.close()

    all_book_names = [row[0] for row in query2_result]

    if not all_book_names:
        return {"error": "책 이름을 가져올 수 없습니다."}

    vectorizer = TfidfVectorizer()
    book_texts = [row[4] for row in book_data]  # Change index to match the description column
    tfidf_matrix = vectorizer.fit_transform(book_texts)
    cosine_similarities = cosine_similarity(tfidf_matrix, tfidf_matrix)

    result = {}

    for target_book_name in all_book_names:
        target_book_idx = None
        for idx, book_row in enumerate(book_data):
            if book_row[0] == target_book_name:
                target_book_idx = idx
                break

        if target_book_idx is None:
            result[target_book_name] = [{"title": f"책 '{target_book_name}'을(를) 찾을 수 없습니다."}]
            continue

        similar_books = [(idx, similarity) for idx, similarity in enumerate(cosine_similarities[target_book_idx]) if
                          similarity >= 0.05]
        similar_books = sorted(similar_books, key=lambda x: x[1], reverse=True)

        similar_book_list = []
        for idx, similarity_score in similar_books:
            if idx != target_book_idx:
                similar_book_title = book_data[idx][0]
                similar_book_similarity = similarity_score
                similar_book_image = book_data[idx][1]
                similar_book_author = book_data[idx][2]
                similar_book_genre = book_data[idx][3]
                similar_book_info = book_data[idx][4]

                similar_book_list.append({
                    "title": similar_book_title,
                    "similarityScore": similar_book_similarity,
                    "image": similar_book_image,
                    "author": similar_book_author,
                    "genre": similar_book_genre,
                    "info": similar_book_info
                })

        result[target_book_name] = similar_book_list

    return result

@app.route('/get_book_recommendations', methods=['GET'])
def get_recommendations():
    result = run_recommendation_algorithm()
    return jsonify(recommendations=result)

if __name__ == '__main__':
    app.run(debug=True, port=4000)
