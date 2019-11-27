<template>
	<view>
		<van-cell v-for="(item, key) in invList" v-bind:key="key" :title="item.name" :value="item.id" size="large" :label="item.description" clickable/>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				invList: []
			}
		},
		onLoad() {
			this.$toast.loading({
				duration: 0,	// 持续展示 toast
				forbidClick: true,	// 禁用背景点击
				message: '加载中'
			});
			
			if(this.$cookies.get("userInfo") != null){	// 获取当前用户信息
				this.userInfo = this.$cookies.get("userInfo");
			}
			var rp = require('request-promise');
			// 获取所有tag
			rp('http://localhost:8080/getInvisibleInv').then(res => {
				// 获取所有Tag成功
				this.invList = JSON.parse(res);
				console.log(this.invList);
				this.$toast.clear();
				this.$toast.success('成功');
			}).catch(err => {
				// 获取失败
				console.log(err);
				this.$toast.clear();
				this.$toast.fail('加载失败，请检查网络连接');
			});
		},
		methods: {
			
		}
	}
</script>

<style>
</style>
