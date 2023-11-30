<template>
	<div>
		这是个人中心
		<ElButton @click="doLogout()">退出登录</ElButton>
	</div>
</template>
<script setup lang="ts">
import { logout } from '~/api/user'
import router from '~/plugins/router'

function doLogout() {
	logout().then((res) => {
		console.log(res)
		if (res.data == null) {
			toast.error('退出登录失败')
		} else {
			localStorage.removeItem('token')
			localStorage.removeItem('playgoerId')
			toast.success('退出登录成功')
			router.replace({ path: '/' })
		}
	})
}
</script>
