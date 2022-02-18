module.exports = {
  devServer: {
    proxy: {
      //"/login": {
      //  target: "http://localhost:8080/",
      //},
      "/api": {
        target: "http://localhost:8081",
      },
    },
  },
};
