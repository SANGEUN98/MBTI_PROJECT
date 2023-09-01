# 목적: 열 이름 재정의, 열순서 재정의
import requests
import json
import pandas as pd
import cx_Oracle

access_key ='ba93000485f503f718ba1b8973bbf818'

def get_request_url():
    url = 'http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=ba93000485f503f718ba1b8973bbf818'
    params = {'key': access_key, 'curPage': 1, 'itemPerPage': 10,
              'dataType': 'JSON', 'openStartDt': '2001', 'openEndDt': '2023'
              }
    response = requests.get(url, params=params)
    # response.content # => response.content는 한글이 인코딩된 형식이므로
    #                       response.text 를 응답받기로함
    return response.text

def json_to_df_info(raw_json):
    all_data = []
    num = 0
    column_list = ["movieCd","movieNm","movieNmEn","prdtYear","openDt","prdtStatNm","nationAlt","genreAlt","repNationNm","repGenreNm","directors","companys"]

    for record in raw_json['movieListResult']['movieList']:
        # 이하 블럭을 자동화 해보세요.
        row_data = []
        for column_data in column_list:
            row_data.append(record.get(column_data))
        all_data.append(row_data)

    return column_list, all_data

# def preprocess_df(df):
#
#     df.insert(0,'date_time', df['baseDate']+df['baseTime'])
#
#     p_df = pd.pivot_table(df, index='date_time', columns=['category'],values='obsrValue')
#     nx = df.loc[0,'nx']
#     ny = df.loc[0,'ny']
#     date_time = df.loc[0,'baseDate'] + ' ' + df.loc[0,'baseTime']
#     p_df.insert(0,'date_time',[date_time])
#     p_df.insert(1,'nx',[nx])
#     p_df.insert(2,'ny',[ny])
#
#     p_df.rename(columns={
#         'date_time':'DATE_TIME', 'nx':'NX', 'ny': 'NY', 'PTY':'강수형태',
#         'REH':'습도','RN1':'시간1_강수량','T1H':'기온',
#         'UUU':'동서바람성분','VEC':'풍향','VVV':'남북바람성분','WSD':'풍속'
#        }, inplace=True)
#
#     redefined_columns = ['DATE_TIME','NX', 'NY', '기온', '시간1_강수량', '강수형태',
#                          '습도', '풍속', '풍향', '동서바람성분', '남북바람성분']
#     p_df = p_df[redefined_columns]
#     return p_df

def preprocessed_df_to_oracle(df):
    con = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
    cur = con.cursor()
    # sql_insert = '''
    #         insert into weather(DATE_TIME, NX, NY, 시간1_강수량, 강수형태, 기온, 습도, 풍향,풍속,동서바람성분,남북바람성분)
    #         values(:DATE_TIME, :NX, :NY, :시간1_강수량, :강수형태, :기온, :습도, :풍향,:풍속,:동서바람성분,:남북바람성분)
    #         '''
    sql_insert = '''
                insert into movie(영화코드, 국문영화명, 영문영화명, 제작연도, 개봉일, 제작상태, 제작국가, 영화장르,대표제작국가명,대표장르명,영화감독,제작사) 
                values(:movieCd, :movieNm, :movieNmEn, :prdtYear, :openDt, :prdtStatNm, :nationAlt, :genreAlt,:repNationNm,:repGenreNm,:directors,:companys)
                '''
    for n in range(0, len(df)):
        movieCd = df.iloc[0]['movieCd']
        movieNm = df.iloc[0]['movieNm']  # int 값에 대해서는 int 형으로 변환해줘야 한다.
        movieNmEn = df.iloc[0]['movieNmEn']
        prdtYear = df.iloc[0]['prdtYear']  # 현재 데이터 프레임의 행인덱스가 date_time이므로 loc가 안된다.
        openDt = df.iloc[0]['openDt']
        prdtStatNm = df.iloc[0]['prdtStatNm']
        nationAlt = df.iloc[0]['nationAlt']
        genreAlt = df.iloc[0]['genreAlt']
        repNationNm = df.iloc[0]['repNationNm']
        repGenreNm = df.iloc[0]['repGenreNm']
        directors = df.iloc[0]['directors']
        companys = df.iloc[0]['companys']

        cur.execute(sql_insert,
                (movieCd, movieNm, movieNmEn, prdtYear, openDt, prdtStatNm,
                 nationAlt, genreAlt, repNationNm, repGenreNm, directors, companys)
                )

    con.commit()
    cur.close()
    con.close()


# url = 'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst'
# # 업데이트는 40~60 사이에 이루어짐
# request_time = get_update_time_info()
# yyyymmdd = request_time.strftime("%Y%m%d")
# hhmm = request_time.strftime("%H%M")

raw_str_json = get_request_url()

if raw_str_json:
    raw_json = json.loads(raw_str_json)

column_list, all_data = json_to_df_info(raw_json)

df = pd.DataFrame(all_data, columns=column_list)

