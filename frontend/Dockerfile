FROM node:14.16-alpine as build

WORKDIR /usr/src/app

COPY package.json package-lock.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx

COPY nginx.conf /etc/nginx/nginx.conf

COPY --from=build /usr/src/app/dist/plasmaDonationFrontend /usr/share/nginx/html

EXPOSE 80