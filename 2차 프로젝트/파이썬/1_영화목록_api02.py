# 목적: 파이썬 코드를 활용하여 OpenAPI 호출 자동화
# Step1: OpenAPI를 제공하는 사이트에서 제공하는 샘플 프로그램을 확보한다.

import requests
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

raw_json = get_request_url()
print(raw_json)