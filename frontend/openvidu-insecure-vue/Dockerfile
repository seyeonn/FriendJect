# build stage
FROM node:14 AS build
WORKDIR /app
COPY package* ./
RUN npm install
COPY public ./public
COPY src ./src
# COPY .env* ./
RUN npm run build

# production stage
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html