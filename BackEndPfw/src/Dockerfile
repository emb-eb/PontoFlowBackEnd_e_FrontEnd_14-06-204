FROM node:latest

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /api

# Copie o código fonte do projeto para o contêiner
COPY ./src /api/src
COPY package.json package-lock.json /api/

# Instale as dependências do projeto
RUN npm install

# Exponha a porta do aplicativo (se necessário)
EXPOSE 3000

# Comando para iniciar o aplicativo quando o contêiner for executado
CMD ["node", "src/index.js"]