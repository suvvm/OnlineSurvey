<template>
	<view>
		<van-button type="default"  @click="testSubmit">发送测试信息</van-button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			testSubmit() {
				var rp = require('request-promise');
				var options = {
				    method: 'POST',
				    uri: 'http://localhost:8080/test',
				    form: {
				        // Like <input type="text" name="name">
				        text1: 'showText1',
						text2: 'showText2'
				    },
				    headers: {
				        /* 'content-type': 'application/x-www-form-urlencoded' */ // Is set automatically
				    }
				};
				rp(options)
				    .then(res => {
				        // POST succeeded...
						console.log(res)
				    })
				    .catch(err => {
				        // POST failed...
						console.log(err)
				    });
			}
		}
	}
</script>

<style>

</style>
