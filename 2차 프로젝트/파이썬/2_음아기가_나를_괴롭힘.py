from googleapiclient.discovery import build
from googleapiclient.errors import HttpError
from oauth2client.tools import argparser
import pandas as pd
import cx_Oracle
import requests
import json

def build_youtube_search(developer_key):
  DEVELOPER_KEY = "AIzaSyBXMz6OWGKGffzDMZEgnaX-vyiSXRuO4pI"
  YOUTUBE_API_SERVICE_NAME="youtube"
  YOUTUBE_API_VERSION="v3"
  return build(YOUTUBE_API_SERVICE_NAME,YOUTUBE_API_VERSION,developerKey=DEVELOPER_KEY)

def get_search_response(youtube, query):
  search_response = youtube.search().list(
    q = 'INFJ플레이리스트',
    # order = "relevance",
    part = "snippet",
    maxResults = 30
    ).execute()
  return search_response

def get_video_info(search_response):
  result_json = {}
  idx =0
  for item in search_response['items']:
    if item['id']['kind'] == 'youtube#video':
      result_json[idx] = info_to_dict(item['id']['videoId'], item['snippet']['title'], item['snippet']['publishedAt'], item['snippet']['thumbnails']['medium']['url'])
      idx += 1
  return result_json

def info_to_dict(videoId, title, publishedAt, url):
  result = {
      "videoId": videoId,
      "title": title,
      "publishedAt": publishedAt,
      "url": url
  }
  return result

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

raw_str_json = get_video_info()
# print(raw_str_json)
# print('디버그용')

if raw_str_json:
    raw_json = json.loads(raw_str_json)

data2=[]
data2=info_to_dict(raw_json)

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