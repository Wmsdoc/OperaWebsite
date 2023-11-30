<template>
	<div>
		<RouterLink v-if="flag" to="user">{{ t('Personal Center') }}</RouterLink>
		<RouterLink v-else to="login">{{ t('login/register') }}</RouterLink>
	</div>
</template>
<script setup lang="ts">
// import { getUserStorage } from '~/api/user'
import { islogin } from '~/api/user'

const { t } = useI18n()
const flag = ref(false)

function getIsLogin() {
	//判断用户是否登录
	const token = localStorage.getItem('token')
	if (token === null || token === '' || token === undefined) {
		return
	} else {
		islogin().then((res) => {
			flag.value = res.data
			if (!res.data) {
				localStorage.removeItem('token')
				localStorage.removeItem('playgoerId')
			}
		})
	}
}

getIsLogin()
</script>
