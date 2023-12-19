<template><div></div></template>
<script setup lang="ts">
import router from '~/plugins/router'

const Route = useRoute() //获取到值
const push = (Route: any) => {
	router.push({ path: '/' + Route.query.path })
}
push(Route)
</script>
