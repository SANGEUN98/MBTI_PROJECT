# 목적: JSON 데이터를 데이터 프레임으로 변환
import pandas as pd
import requests
import datetime
import json

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
# def get_update_time_info(now):
#     minute = now.minute
#
#     if minute >= 40 and minute < 60:
#         day_time = now.strftime("%H%M")
#     else:
#         # timedelta로 1시간을 빼야 안전하다.
#         # 예) 00시 10분에 데이터를 업데이트하다면 전날 23시 10분이 되어야 하기 때문
#         modified_time = now - datetime.timedelta(hours=1)
#         day_time = modified_time.strftime("%H%M")
#     return day_time

def json_to_df_info(raw_json):
    all_data = []
    column_list = ["movieCd","movieNm","movieNmEn","prdtYear","openDt","prdtStatNm","nationAlt","genreAlt","repNationNm","repGenreNm","directors","companys"]

    for record in raw_json['movieListResult']['movieList']:
        all_data.append(
            [
                record.get("movieCd"),
                record.get("movieNm"),
                record.get("movieNmEn"),
                record.get("prdtYear"),
                record.get("openDt"),
                record.get("prdtStatNm"),
                record.get("nationAlt"),
                record.get("genreAlt"),
                record.get("repNationNm"),
                record.get("directors"),
                record.get("companys")
            ]
        )
    return column_list, all_data

# def preprocess_df(df):
#     # * Preprocess(전처리): 데이터 포멧 변경될 필요가 있음
#     # baseDate	baseTime	category	nx	ny	    obsrValue
#     # 20221227	1200	    PTY	        58	125	    0
#     # 20221227	1200	    REH	        58	125	    41
#     #  ....................
#     #
#     # date_time	 nx	ny	PTY	REH	RN1	T1H	UUU	VEC	VVV	WSD
#     # 2022122712 00	58	125	0	41	0	2.7	0.3	201	0.8	0.9
#
#
#     # p_df = pd.DataFrame(df, index='openDt', columns=['genreAlt'])
#
#     return p_df


# now = datetime.datetime.now()
# # yyyymmdd = '20221227'
# base_date = now.strftime('%Y%m%d')
# # day_time = '1102'
# day_time = get_update_time_info(now);
# day_time = time.strftime('%H%M')
# 00분~40분 구간은 최신 정보가 없음 / 41분~59분 구간은 최신 정보가 있음
raw_str_json = get_request_url()

if raw_str_json:
    raw_json = json.loads(raw_str_json)
    # json.loads()문자열 json을 실제 json(dict) 타입으로 변환

column_list, all_data = json_to_df_info(raw_json)

df = pd.DataFrame(all_data,columns=column_list)
# df_preprocessed = preprocess_df(df)

file_name = f'영화목록.csv'

df.to_csv(file_name, index=False)