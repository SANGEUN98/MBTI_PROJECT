import requests
access_key ='ba93000485f503f718ba1b8973bbf818'
def get_request_url(url, yyyymmdd, hhmm):
    params = {'key': access_key, 'targetDt': yyyymmdd, 'itemPerPage': 10,
              'dataType': 'JSON'
              }
    response = requests.get(url, params=params)
    # response.content # => response.content는 한글이 인코딩된 형식이므로
    #                       response.text 를 응답받기로함
    return response.text

def abc_cut(raw_json):
    data=[]
    num=0
    column_list = ["rank","rankInten","rankOldAndNew","movieCd","movieNm","openDt","audiCnt","audiInten","audiAcc"]

    for record in raw_json['boxOfficeResult']['dailyBoxOfficeList']:
        # 이하 블럭을 자동화 해보세요.
        row_data = []
        for column_data in column_list:
            row_data.append(record.get(column_data))
        data.append(row_data)
    return data

def get_rank_request_url(url,yyyymmdd,hhmm):
    params = {'key': access_key, 'targetDt': yyyymmdd, 'itemPerPage': 10,
              'dataType': 'JSON'
              }
    print("get_rank 이상무")
    response = requests.get(url, params=params)
    # response.content # => response.content는 한글이 인코딩된 형식이므로
    #                       response.text 를 응답받기로함
    return response.text

def abc_rank_cut(raw_json):
    data = []
    num = 0
    column_list = ["rank", "movieNm"]

    for record in raw_json['boxOfficeResult']['dailyBoxOfficeList']:
        # 이하 블럭을 자동화 해보세요.
        row_data = []
        for column_data in column_list:
            row_data.append(record.get(column_data))
        data.append(row_data)
    return data