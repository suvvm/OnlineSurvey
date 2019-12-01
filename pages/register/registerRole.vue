<template>
	<view>
		<van-steps :active="active">
			<van-step>注册账号</van-step>
			<van-step>选择角色</van-step>
			<van-step>等待确认</van-step>
			<van-step>注册完成</van-step>
		</van-steps>
	
		<van-radio-group v-model="radio">
			<van-cell-group>
				<van-cell title="我是普通用户" clickable @click="radio = '1'">
					<van-radio slot="right-icon" name="1" />
				</van-cell>
				<van-cell title="我是公司用户" clickable @click="radio = '2'">
					<van-radio slot="right-icon" name="2" />
				</van-cell>
				<van-cell title="我想入职管理" clickable @click="radio = '3'">
					<van-radio slot="right-icon" name="3" />
				</van-cell>
			</van-cell-group>
		</van-radio-group>
		<van-button type="primary" size="large" @click="submit">下一步</van-button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				active: 1,
				radio: 2,
			}
		},
		onLoad() {
	
		},
		methods: {
			submit() {
				if(this.radio == 1) {
					this.$router.push({
						path: '/pages/register/registerOrdinaryUsers', 
					});
				} else if(this.radio == 2) {
					this.$router.push({
						path: '/pages/register/registerCompany', 
					});
				} else {
					this.$router.push({
						path: '/pages/register/registerAdmin', 
					});
				}
			}
		}
	}
</script>

<style>
</style>
