<script setup lang="ts">
import { getRoutes } from '@/plugins/router'
import { SwitchIcon } from 'vue-dark-switch'

const { te, t } = useI18n()

const routes = getRoutes()
	.filter(
		(r) =>
			!r.path.includes('notFound') &&
			!r.path.includes('user') &&
			!r.path.includes('login') &&
			!r.path.includes('register') &&
			!r.path.includes('forgetPassword')&&
			!r.path.includes('opera'),
	)
	.map((r) => {
		let { path, name } = r
		if (path === '/') {
			return { path, name: 'home' }
		}

		if (!name) {
			name = path
		}

		return { path, name: name.toString().slice(1).replaceAll('/', ' · ') }
	})
</script>

<template>
	<nav
		aria-label="Site Nav"
		class="mx-auto h-80px max-w-3xl flex items-center justify-between p-4"
	>
		<span class="h-10 w-10 flex items-center justify-center">
			<SwitchIcon unmount-persets />
		</span>

		<ul class="flex items-center gap-2 text-sm font-medium">
			<li v-for="r of routes" :key="r.path" class="hidden !block">
				<RouterLink class="rounded-lg px-3 py-2" :to="r.path">
					{{ te(r.name) ? t(r.name) : r.name }}
				</RouterLink>
			</li>
			<li class="hidden !block">
				<UserCenter />
			</li>
			<li class="hidden !block">
				<Dropdown />
			</li>
		</ul>
	</nav>
</template>
