FROM node:lts-alpine as build-stage
WORKDIR /app

COPY . .
RUN npm install
RUN npm run build

FROM nginx:alpine
COPY --from=build-stage /app/dist /var/www/html/dist

EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]
