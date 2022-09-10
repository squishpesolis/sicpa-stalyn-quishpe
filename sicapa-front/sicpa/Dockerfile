# Stage 1
FROM node:18.4.0-alpine as build
ARG apiURL
ENV apiURL=$apiURL
LABEL stage=builder
WORKDIR /usr/src/app
COPY package.json ./
RUN npm config set registry http://registry.npmjs.org/
RUN npm install --legacy-peer-deps
COPY . .
RUN npm run build --prod


### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /usr/src/app/dist/sicpa /usr/share/nginx/html
EXPOSE 80
EXPOSE 443
ENTRYPOINT ["nginx", "-g", "daemon off;"]