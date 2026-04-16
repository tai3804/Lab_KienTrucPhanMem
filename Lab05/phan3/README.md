# Phan 3 - Docker Compose Exercises

Moi bai tap nam trong mot thu muc `cauXX`.

Lenh huu ich:

```powershell
docker compose -f docker-compose.yaml config
docker compose up -d
docker compose logs -f
docker compose down -v
```

Bai 6 su dung 2 file:

```powershell
docker compose -f docker-compose-dev.yml up
docker compose -f docker-compose-prod.yml up --build
```
