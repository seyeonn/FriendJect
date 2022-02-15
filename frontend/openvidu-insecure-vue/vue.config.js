const path = require("path");
module.exports = {
  // devServer: {
  //   proxy: {
  //     "/login": {
  //       target: "http://localhost:8080/",
  //     },
  //   },
  // },
  outputDir: path.resolve(__dirname, "./testDist"),
};

// module.exports = {
//   outputDir: path.resolve(__dirname, "./testDist"),

//   //   assetsDir: "./"
//   //   asset 파일 위치 설정
// };
