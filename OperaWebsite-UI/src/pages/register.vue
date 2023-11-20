<template>
	<div class="login-container">
		<!-- 登录表单 -->
		<el-card
			class="z-1 !border-none w-100 !bg-transparent !rounded-4% <sm:w-83"
		>
			<div>
				<el-form :model="form" label-width="120px">
					<h3 class="title">戏曲网-注册</h3>
					<el-form-item label="Username">
						<!-- <el-icon><User /></el-icon> -->
						<el-input v-model="form.username" type="text" />
					</el-form-item>
					<el-form-item label="Password">
						<!-- <el-icon><Unlock /></el-icon> -->
						<el-input v-model="form.password" type="password" />
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="handleRegister()">{{
							t('register')
						}}</el-button>
						<el-button @click="handleLogin()">{{
							t('back to login')
						}}</el-button>
					</el-form-item>
				</el-form>
			</div>
		</el-card>
	</div>
</template>

<script setup lang="ts">
import router from '~/plugins/router'

const { t } = useI18n()
const form = reactive({
	username: '',
	password: '',
})

function handleLogin() {
	router.replace({ path: '/login' })
}

function handleRegister(): void {
	// router.replace({ path: '/register' })
}

onMounted(() => {})
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
	background-image: url('../assets/images/login-background.jpg');
	background-size: cover;
}
.title {
	margin: 0px auto 30px auto;
	text-align: center;
	color: #707070;
}

.login-form {
	border-radius: 6px;
	background: #ffffff;
	width: 400px;
	padding: 25px 25px 5px 25px;
	.el-input {
		height: 38px;
		input {
			height: 38px;
		}
	}
	.input-icon {
		height: 39px;
		width: 14px;
		margin-left: 2px;
	}
}
</style>
