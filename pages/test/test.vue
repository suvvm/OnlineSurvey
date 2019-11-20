<template>
	
	<view>
		
		<van-tabbar 
		  route
		  active-color="#07c160"
		  inactive-color="#000"
		>
		  <van-tabbar-item icon="home-o">标签</van-tabbar-item>
		  <van-tabbar-item icon="search">标签</van-tabbar-item>
		  <van-tabbar-item icon="setting-o">标签</van-tabbar-item>
		  <van-tabbar-item icon="manager">我的</van-tabbar-item>
		</van-tabbar>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				value: 50
			}
		},
		methods: {
			onChange(value) {
				this.$toast('当前值：' + value);
			}
		}
	}
</script>

<style>

</style>
