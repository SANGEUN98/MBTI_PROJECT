# 목적: 업데이트 주기에 맞추어 시간 파라메터 설정
# OpenAPI에서 최신 데이터를 가저오는 테크닉

import requests
import datetime
# import time
import json

# Encoding Key
# access_key = 'bgOnt78reFNsTUJuAwlI30JDObTxX6hbJCxyApJCtuf3xjJZJ%2FmOs8Vhg3GZAsLc1fXTkQ9sjq0mTEupWDdyyA%3D%3D'
# Decoding key
access_key ='ba93000485f503f718ba1b8973bbf818'

def get_request_url():
    url = 'http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=ba93000485f503f718ba1b8973bbf818'
    params = {'key': access_key, 'curPage': 1, 'itemPerPage': 10,
              'dataType': 'JSON', 'openStartDt': '2001', 'openEndDt': '2023'
              # 'repNationCd': '22041011', 'movieTypeCd': '220101'
              }
    response = requests.get(url, params=params)
    # response.content # => response.content는 한글이 인코딩된 형식이므로
    #                       response.text 를 응답받기로함
    return response.text
def get_parsed_json(raw_json) :
    all_data = []

    for record in raw_json['movieListResult']['movieList']:
        all_data.append(
            {"movieCd": record.get("movieCd") ,
             # {"baseDate": record["baseDate"],
             "movieNm": record.get("movieNm"),
             "movieNmEn": record.get("movieNmEn"),
             "prdtYear": record.get("prdtYear"),
             "openDt": record.get("openDt"),
             "prdtStatNm": record.get("prdtStatNm"),
             "nationAlt": record.get("nationAlt"),
             "genreAlt": record.get("genreAlt"),
             "repNationNm": record.get("repNationNm"),
             "repGenreNm": record.get("repGenreNm"),
             "directors": record.get("directors"),
            "companys": record.get("companys")}
        )
    return all_data

def get_update_time_info(now):
    minute = now.minute

    if minute >= 40 and minute < 60:
        day_time = now.strftime("%H%M")
    else:
        # timedelta로 1시간을 빼야 안전하다.
        # 예) 00시 10분에 데이터를 업데이트하다면 전날 23시 10분이 되어야 하기 때문
        modified_time = now - datetime.timedelta(hours=1)
        day_time = modified_time.strftime("%H%M")
    return day_time

now = datetime.datetime.now()
# yyyymmdd = '20221227'
base_date = now.strftime('%Y%m%d')
# day_time = '1102'
day_time = get_update_time_info(now);
# day_time = time.strftime('%H%M')
# 00분~40분 구간은 최신 정보가 없음 / 41분~59분 구간은 최신 정보가 있음
raw_str_json = get_request_url()

if raw_str_json:
    raw_json = json.loads(raw_str_json)
    # json.loads()문자열 json을 실제 json(dict) 타입으로 변환

parsed_json = get_parsed_json(raw_json)

file_name = f'영화목록_{base_date}_{day_time}.json'

with open(file_name, 'w', encoding='utf8') as outfile:
    retJson = json.dumps(parsed_json, indent=4, sort_keys=True, ensure_ascii=False)
    outfile.write(retJson)

print(f'{file_name} SAVED\n')