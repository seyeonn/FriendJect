module.exports = {
  devServer: {
    proxy: {
      "/login": {
        target: "http://localhost:8080/",
      },
    },
  },
};
