```yaml
  kestra:
    image: kestra/kestra:latest-full
    pull_policy: always
    entrypoint: /bin/bash
    user: "root"
    env_file:
      - .env
    command:
      - -c
      - /app/kestra server standalone --worker-thread=128
    volumes:
      - kestra-data:/app/storage
      - /var/run/docker.sock:/var/run/docker.sock
      - /tmp/kestra-wd:/tmp/kestra-wd:rw
    environment:
      KESTRA_CONFIGURATION: |
        datasources:
          postgres:
            url: jdbc:postgresql://postgres:5432/kestra
            driverClassName: org.postgresql.Driver
            username: kestra
            password: k3str4
        kestra:
          server:
            basic-auth:
              enabled: false
              username: admin
              password: kestra
          repository:
            type: postgres
          storage:
            type: local
            local:
              base-path: "/app/storage"
          queue:
            type: postgres
          tasks:
            tmp-dir:
              path: /tmp/kestra-wd/tmp
            scripts:
              docker:
                volume-enabled: true # 👈 this is the relevant setting
```
```yaml
  kestra:
    image: kestra/kestra:latest-full
    pull_policy: always
    entrypoint: /bin/bash
    user: "root"
    env_file:
      - .env
    command:
      - -c
      - /app/kestra server standalone --worker-thread=128
    volumes:
      - kestra-data:/app/storage
      - /var/run/docker.sock:/var/run/docker.sock
      - /tmp/kestra-wd:/tmp/kestra-wd:rw
    environment:
      KESTRA_CONFIGURATION: |
        kestra:
          tasks:
            scripts:
              docker:
                volume-enabled: true
```