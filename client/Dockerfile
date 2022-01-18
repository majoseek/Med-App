FROM node:10-alpine

# Create app directory
WORKDIR /usr/src/app

COPY ./package-docker.json ./package.json

COPY package-lock.json ./

RUN npm ci --only=production
COPY ./src ./src
COPY ./public ./public

EXPOSE 3000
CMD [ "npm", "start" ]