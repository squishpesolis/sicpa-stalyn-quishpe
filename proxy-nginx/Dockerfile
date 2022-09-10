
FROM nginx:1.17.1-alpine
COPY default.conf /etc/nginx/conf.d/default.conf
EXPOSE 81
ENTRYPOINT ["nginx", "-g", "daemon off;"]