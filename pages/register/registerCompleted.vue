<template>
	<view>
		<van-steps :active="active">
			<van-step>注册账号</van-step>
			<van-step>选择角色</van-step>
			<van-step>等待确认</van-step>
			<van-step>注册完成</van-step>
		</van-steps>
		
		<van-button class="nex" type="info" size="large" @click="submit">立刻体验</van-button>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				active: 3,
				userInfo: {},
			}
		},
		onLoad() {
			if(this.$cookies.get("userInfo") != null){
				this.userInfo = this.$cookies.get("userInfo");
			}
		},
		methods: {
			submit() {
				this.$router.push({
					path: '/pages/user/user', 
				})
			}
		}
	}
</script>

<style>
	.nex{
		margin-top: 100rpx;
	}
</style>
