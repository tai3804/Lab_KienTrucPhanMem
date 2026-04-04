# Stage 1: Build source code
FROM node:18 AS build-stage
WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build

# Stage 2: Run built application
FROM node:18-alpine
WORKDIR /app

RUN npm install -g serve

COPY --from=build-stage /app/build ./build

EXPOSE 3000
CMD ["serve", "-s", "build", "-l", "3000"]