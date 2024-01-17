<template>
	<div class="login-container">
		<!-- 登录表单 -->
		<el-card class="el-card">
			<div>
				<el-form :model="form" label-width="120px">
					<h3 class="title">戏曲网-登录</h3>
					<el-form-item label="用户名">
						<!-- <el-icon><User /></el-icon> -->
						<el-input v-model="form.username" type="text" />
					</el-form-item>
					<el-form-item label="密&emsp;码">
						<!-- <el-icon><Unlock /></el-icon> -->
						<el-input v-model="form.password" type="password" />
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="handleLogin">{{
							t('login')
						}}</el-button>
						<el-button @click="handleRegister">{{ t('register') }}</el-button>
					</el-form-item>
				</el-form>
			</div>
		</el-card>
	</div>
</template>

<script setup lang="ts">
import router from '~/plugins/router'
import { login } from '~/api/user'

const { t } = useI18n()
const form = ref({
	username: '',
	password: '',
})
/**
 * 登录
 */
function handleLogin() {
	if (form.value.username == '') {
		toast.warning('请输入用户名')
		return
	} else if (form.value.password == '') {
		toast.warning('请输入密码')
		return
	}
	let data = JSON.stringify(form.value)
	login(data).then((res) => {
		if (res.data == null) {
			toast.error('登录失败,请检查用户名或密码是否正确')
			form.value.password = ''
		} else {
			console.log(res.data)
			// 登录成功后将用户信息存入localStorage
			localStorage.setItem('token', res.data.tokenValue)
			localStorage.setItem('playgoerId', res.data.loginId)
			toast.success('登录成功!!!')
			// 有上一页则返回
			if (window.history.state.back) {
				router.back()
			} else {
				// 没有上一页则返回到首页
				router.replace({ path: '/' })
			}
		}
	})
}

function handleRegister(): void {
	router.replace({ path: '/register' })
}

onMounted(() => {})
</script>

<style scoped>
.login-container::after {
	content: '';
	background: url('~/assets/images/login-bg.jpg') no-repeat center center;
	background-size: cover;
	opacity: 0.5;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	position: absolute;
	z-index: -1;
}
.login-container {
	width: 100vw;
	height: calc(100vh - 80px);
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}
.title {
	margin: 0px auto 30px auto;
	text-align: center;
	color: #333333;
}
.el-card {
	width: 500px;
	height: 300px;
	background-color: white;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
	border-radius: 6px;
	padding: 20px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	.el-input {
		width: 80%;
		margin: auto;
	}

	.el-form-item {
		display: flex;
		justify-content: center;
	}
}

.login-form {
	border-radius: 6px;
	background: #ffffff;
	width: 400px;
	padding: 25px 25px 5px 25px;
	padding: 20px;
	font-size: 16px;
	color: #333;
	transition: all 0.3s ease;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-between;
}
</style>
