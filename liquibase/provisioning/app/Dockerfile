# https://hub.docker.com/_/openjdk/
# MAVEN Build Stage
FROM maven:3.8.3-openjdk-17-slim as maven
LABEL maintainer="iskrypnyk@axon.dev"

# Copy project source
COPY . /app
WORKDIR /app

# Building Project Artifact 
RUN mvn clean install

# JAVA Build Stage
FROM openjdk:17.0.2-jdk-slim
LABEL maintainer="iskrypnyk@axon.dev"

# Font config /app
RUN apt-get update \
    && apt-get install -y fontconfig libfreetype6 \
    && rm -rf /var/lib/apt/lists/*

# Init vars for non-root user
ARG USERNAME=user
ARG USER_UID=2000
ARG USER_GID=$USER_UID

# Create the non-root user
RUN groupadd --gid $USER_GID $USERNAME \
    && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME

# Copy java artifact from previous stage
COPY --from=maven --chown=user:user /app/playground-api/target/playground.jar /app/playground.jar

# Create directory for jwt keys storage
RUN mkdir -p /resources/jwt \
    && chown -R user:user /resources 

WORKDIR /app

USER user

SHELL ["/bin/bash", "-c"]

ENTRYPOINT echo -e "-----BEGIN RSA PRIVATE KEY-----\n$APP_JWT_PRIVATE_KEY\n-----END RSA PRIVATE KEY-----" > /resources/jwt/private_key.pem \
           && openssl pkcs8 -topk8 -inform PEM -outform DER -in /resources/jwt/private_key.pem -out $PRIVATE_KEY_GENERATION_PATH -nocrypt \
           && openssl rsa -in /resources/jwt/private_key.pem -pubout -outform DER -out $PUBLIC_KEY_GENERATION_PATH \
           && java -jar "-Dspring.datasource.url=jdbc:postgresql://$DB_HOST:$DB_PORT/$DB_NAME" \
                        "-Dspring.datasource.username=$DB_USERNAME" \
                        "-Dspring.datasource.password=$DB_PASSWORD" \
                        "-Dspring.profiles.active=$PROFILE" \
                        "-Dplayground.security.public-key-path=$PUBLIC_KEY_PATH" \
                        "-Dplayground.security.private-key-path=$PRIVATE_KEY_PATH" \
			"-Dplayground.email.api-key=$SENDGRID_API_KEY" \
			"-Dplayground.email.sender=$SENDGRID_SENDER" \
                        "-Dplayground.messaging.web-portal-url=$WEB_PORTAL_URL" \
                        "-Dplayground.security.access-token-ttl-min=$ACCESS_TOKEN_TTL_MIN" \
			"-Dplayground.security.refresh-token-ttl-min=$REFRESH_TOKEN_TTL_MIN" \
			"-Dplayground.aws-config.service-endpoint=$AWS_SERVICE_ENDPOINT" \
			"-Dplayground.aws-config.bucket-name=$AWS_BUCKET_NAME" \
			"-Dplayground.aws-config.region-name=$AWS_REGION_NAME" \
			"-Dspringdoc.api-docs.enabled=$SWAGGER_UI" \
			"-Dplayground.geocoding.mapbox.access-token=$MAPBOX_ACCESS_TOKEN" \
                        /app/playground.jar
