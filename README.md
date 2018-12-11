### Solution
I see, there is usually less than 40 of preorders in API v1.
I decided to load all to cache (EhCache) and implement List paging.
 
### Tests run
```
gradle task :api:test
```

### Run with gradle
``` 
gradle task :core:bootRun
gradle task :web:npmDev
```

### Run with docker-compose
```
docker-compose up -d
```

### Run with kubernetes
```
minikube start
minikube config set vm-driver [your vm]
kubectl config use-context minikube

kubectl apply -f k8s/kinguin-api.yaml
kubectl apply -f k8s/kinguin-web.yaml

kubectl get pod

# when pods are `runnig`, open browser
open $(minikube service --url kinguin-web)
```

### Performance tests (after cache warm up is faster):
```
U-779:kinguin deusz$ ab -n 1000 -c 20 http://localhost:9080/api/v2/preorders?page=2&sort=name
[1] 39913
U-779:kinguin deusz$ This is ApacheBench, Version 2.3 <$Revision: 1826891 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            9080

Document Path:          /api/v2/preorders?page=2
Document Length:        4793 bytes

Concurrency Level:      20
Time taken for tests:   2.840 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      4916000 bytes
HTML transferred:       4793000 bytes
Requests per second:    352.15 [#/sec] (mean)
Time per request:       56.794 [ms] (mean)
Time per request:       2.840 [ms] (mean, across all concurrent requests)
Transfer rate:          1690.59 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.6      0       8
Processing:    11   56  17.5     53     128
Waiting:       10   55  17.5     52     128
Total:         12   56  17.4     53     128

Percentage of the requests served within a certain time (ms)
  50%     53
  66%     58
  75%     62
  80%     66
  90%     83
  95%     95
  98%    105
  99%    112
 100%    128 (longest request)
```