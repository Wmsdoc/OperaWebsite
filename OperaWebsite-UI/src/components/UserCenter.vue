<template>
	<div>
		<RouterLink v-if="flag" to="user">{{ t('Personal Center') }}</RouterLink>
		<RouterLink v-else to="login">{{ t('login/register') }}</RouterLink>
	</div>
</template>
<script setup lang="ts">
const { t } = useI18n()
let flag = false

function isLogin(): void {
	// localStorage.setItem('token', '123456')
	console.log(localStorage.getItem('token'))
	//判断用户是否登录
	console.log('isLogin')
	const token = localStorage.getItem('token')
	if (token != null) {
		flag = true
	}
}

isLogin()
</script>
