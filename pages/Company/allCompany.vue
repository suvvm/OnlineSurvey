<template>
	<view>
		<van-cell-group v-for="(item, key) in companies" :key="key">
			<van-cell title="公司名" icon="coupon-o" :value="item.name"/>
			<van-cell title="公式标签" icon="bookmark-o" >
				<van-tag type="danger">{{item.forms}}</van-tag>/ 
				<van-tag type="primary">{{item.domain}}</van-tag>
			</van-cell>
			<van-cell title="公司简介" icon="comment-o" :label="item.description"/>
		</van-cell-group>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				companies: []
			}
		},
		onLoad() {
			var rp = require('request-promise');
			var options = {	// 根据id获取用户包括头像与人脸数据的全部信息
			    method: 'POST',
			    uri: 'http://101.201.70.76:8211/getCompanies'
			};
			rp(options).then(res => {	// 请求成功
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取公司信息失败，未找到公司信息');
				} else {
					this.companies = JSON.parse(res);
					console.log(this.companies);
					this.$toast.success('加载成功');
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {	// 请求失败
				this.$toast.clear();
				this.$toast.fail('加载失败, 请检查网络连接');
				console.log(err);
			});
		},
		methods: {
			
		}
	}
</script>

<style>
</style>
