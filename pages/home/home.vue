<template>
	<view>
		<!-- 底部导航栏 -->
		<van-tabbar route>
		  <van-tabbar-item icon="home-o" to="/pages/home/home">首页</van-tabbar-item>
		  <van-tabbar-item icon="search" to="/pages/search/search">检索</van-tabbar-item>
		  <van-tabbar-item icon="friends-o" to="/pages/categories/categories">标签</van-tabbar-item>
		  <van-tabbar-item icon="user-o" to="/pages/user/user">我的</van-tabbar-item>
		</van-tabbar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
