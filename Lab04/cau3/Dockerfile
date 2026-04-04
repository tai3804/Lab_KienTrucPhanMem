FROM node:18-alpine

WORKDIR /app/react-app

COPY react-app/package*.json ./
RUN npm ci

COPY react-app ./

EXPOSE 3000
CMD ["npm", "start"]