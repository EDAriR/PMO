import json

import requests
from bs4 import BeautifulSoup

# url = "http://localhost:8080/"
# url = "http:///172.17.9.60:8080/aaa"
url = "http:///172.17.6.118:8080/aaa"
api = "aaa"
headers = {
    'content-type': 'application/json'
}

body = {
    'userId': 'systemAdmin',
    'password': '1qaz2wsx',
    'userAgent': 'WEB',
}


urlll = 'http:///172.17.6.118:8080/aaa/forSystem/sync/user'

requests.put(urlll, data=body)
# res = requests.post(url + api, data = json.dumps(body), headers=headers)

# soup1 = BeautifulSoup(res.text, "html.parser")

print(url)

print('+=============================================+')
# print(body.get('userId'))
# print(soup1)
print('+=============================================+')
