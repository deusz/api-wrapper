### Rozwiązanie

Z tego co zauważyłem, preordery są wystawiane w niewielkich ilościach.
Zdecydowałem się je załadować do cache'a w pamieci. Na ten moment uzywam ConcurrentHashMap,
Ale trzeba skonfigurowac ehcache + TTL.

### Uruchomienie
```
docker-compose up -d
```

lub
```
minikube start
minikube config set vm-driver [your vm]
kubectl config use-context minikube

kubectl get pod

# gdy pody sa juz runnig, to sprawdz adres frontendu
minikube service --url kinguin-web
```