<template>
	<view>
		<van-steps :active="active">
			<van-step>注册账号</van-step>
			<van-step>选择角色</van-step>
			<van-step>等待确认</van-step>
			<van-step>注册完成</van-step>
		</van-steps>
		<van-skeleton
		  title
		  avatar
		  :row="3"
		  :loading="loading"
		>
		
			<van-row gutter="20">
			  <van-col offset="1" span="6">
				  <van-image
				  class="avatar"
				  round
				  width="3rem"
				  height="3rem"
				  src="https://img.yzcdn.cn/vant/cat.jpeg"
				/>
				
			</van-col>
			  <van-col ><h3>欢迎您suvvm</h3></van-col>
			</van-row>
			
			<van-row>
			  <van-col offset="8">您的姓名：</van-col>
			</van-row>
			<van-row>
			  <van-col offset="8">您的电话：</van-col>
			</van-row>
			<van-row>
			  <van-col offset="8">您的邮箱：</van-col>
			</van-row>
		</van-skeleton>
		<van-button type="primary" size="large" @click="show">show</van-button>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				active: '2',
				loading: true
			}
		},
		onLoad() {
	
		},
		methods: {
			show() {
				this.loading = false;
			}
		}
	}
</script>

<style>
	
</style>
