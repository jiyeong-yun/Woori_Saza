# 포팅 매뉴얼

# 1. 빌드 및 배포

- Front-end
- Nginx 1.18.0
- Node.js 16.13.0
- Npm 8.1.0
- VS Code 1.64.2

```bash
cd Front-end/woorisaza
npm install
npm run serve
```

```bash
cd Front-end/woorisaza
npm install
npm run build
```

- Back-end
- Java openjdk 1.8.0_192
- SpringBoot 2.6.2, 기본 포트 8080
- IntelliJ IDEA 2021.3.1 (Ultimate Edition)

```bash
cd Back-end
mvn install
```

```bash
cd Back-end
mvn package
```

- Node.js 16.13.0, 기본 포트 3000
- Npm 8.10
- VS Code 1.64.2

```bash
cd Back-end/SimpleUpload
npm install
node SimpleImageUpload.js
```

- MariaDB 10.3.32
- 데이터베이스 이름 : woorisaza

# 2. 외부 서비스 정보

- 카카오 소셜 로그인 API
    - 카카오 개발자 웹 사이트에서 API 키를 발급받아 사용
- Google Maps Geocoding API
    - 결제 수단을 등록한 후 웹 사이트에서 API 키를 발급받아 사용
- VirusTotal
    - 회원가입 후 웹 사이트에서 API 키를 발급받아 사용

# 3. DB 덤프 파일
[DB 덤프 파일](./woorisaza_dump.sql)
# 4. 시연 시나리오
[시연 시나리오](./woorisaza_scenario.pdf)
