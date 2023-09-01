import threading
import time
from datetime import datetime, timedelta
import pandas as pd
import cx_Oracle
import requests
import json
# access_key = 'HVgh0GJtfha0bssIG8/oBb7dkuTWWgOnt3o47r4Wa1/SrD6VRDqJ0cOzT/6T4vL3KX4JV0bKzNZl9WqYpOdLJg=='
access_key ='AIzaSyBXMz6OWGKGffzDMZEgnaX-vyiSXRuO4pI'
def get_request_url():
    print(2)
    url = 'https://www.googleapis.com/youtube/v3/search'
    params = {'key': access_key, 'part': 'snippet', 'maxResults': 30,
              'dataType': 'JSON', 'q': 'INFJ플레이리스트', 'type': 'video'
              }
    print(3)
    response = requests.get(url, params=params)
    return response.text

def abc_cut(j):
    data=[]
    num=0
    column_list = ["url"]


    for record in raw_json["items"]["snippet"]:
        # 이하 블럭을 자동화 해보세요.
        row_data = []
        for column_data in column_list:
            row_data.append(record.get(column_data))
        data.append(row_data)


    return data

def preprocessed_df_to_oracle(df):
    con = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
    cur = con.cursor()
    sql_insert = '''
                    insert into music( URL) 
                    values(:url)
                    '''
    for n in range(0, len(df)):

        url = df.iloc[n]['url']

        cur.execute(sql_insert,
                    ( url)
                    )

    con.commit()
    cur.close()
    con.close()

raw_str_json = get_request_url()
# print(raw_str_json)
# print('디버그용')

if raw_str_json:
    raw_json = json.loads(raw_str_json)

data2=[]
data2=abc_cut(raw_json)

column_list = ["url"]

df = pd.DataFrame(data2[0])
df3= pd.DataFrame(data2)
print(df3)
# for p in range(1,len(data2)+1):
#     df.append(data2[p])




# print(data2)
# df2=df3.transpose()
num=0
for l in column_list:
    # print(l)
    df3.rename(columns={num:l} , inplace=True)
    num+=1

# print(df2['so2Grade'])

# df3=df2.set_index(column_list)
# df = pd.DataFrame(data2, columns=column_list)
print(df3)
preprocessed_df_to_oracle(df3)



















