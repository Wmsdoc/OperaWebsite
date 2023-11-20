<template>
	<div>
		<div>这是分类</div>
		<div>{{ data }}</div>
	</div>
</template>
<script setup lang="ts">
const { data } = getAllOperaType()
console.log(data)
</script>
