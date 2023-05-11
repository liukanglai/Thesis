import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

// import "~/styles/element/index.scss";

// import ElementPlus from "element-plus";
// import all element css, uncommented next line
// import "element-plus/dist/index.css";

// or use cdn, uncomment cdn link in `index.html`

import "~/styles/index.scss";
import "uno.css";

// If you want to use ElMessage, import it.
import "element-plus/theme-chalk/src/message.scss";
import axios from "axios";

const app = createApp(App);
// app.use(router);
// app.mount("#app");
// 设置反向代理，前端请求默认发送到 http://localhost:8888/api
axios.defaults.baseURL = "http://localhost:8080/api";
app.use(router).mount("#app");
