<template>
	<div class="rounded-lg px-3 py-2" @click="doLogin()">登录</div>
</template>
<script lang="ts">
// import { apiIsLogin } from "~/api/UserCenter";
export default {
	name: "UserCenter",
	setup() {
		return {};
	},
	mounted() {
		// apiIsLogin();
		http.get("/api/isLogin").then((res) => {
			console.log(res);
		});
		console.log("UserCenter mounted");
	},
	methods: {
		isLogin() {
			//判断用户是否登录
			console.log("isLogin");
		},
		doLogin() {
			console.log("doLogin");
		},
	},
};
</script>
