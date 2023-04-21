# 이미지 다운받기
FROM mariadb 
# 환경 변수 선언
ENV TZ Asia/seoul
ENV MYSQL_ROOT_PASSWORD=12345
ENV MYSQL_DATABASE=webdev
# 바로가기 경로 만들기
RUN ln -snf /usr/share/zoneinfo/${TZ} /etc/localtime && echo $TZ > /etc/timezone
# db_xx.sql 파일을 뒤 경로로 넣기
ADD db_user.sql /docker-entrypoint-initdb.d/01_db_user.sql
ADD db_board.sql /docker-entrypoint-initdb.d/02_db_board.sql
# 작업 디렉토리 경로
WORKDIR /
EXPOSE 3306
