<template>
	<view>
		<van-panel :title="invName" :desc="invDescription" :status="statusText">
			<view v-for="(item,key) in invDetails" :key="key">
				<van-panel v-if="item.type == 0"  :title="item.stem" status="选择题">
					<!-- <view v-for="(opt,num) in item.options" :key="num">
						<van-field :value="opt.value" :label="opt.option" disabled/>
					</view> -->
					<van-checkbox-group v-model="result.resultlist[key]" :max="1">
					  <van-cell-group>
					    <van-cell v-for="(opt,num) in item.options" clickable :key="num" :title="opt.value" @click="toggle(key, num)" >
					      <van-checkbox :name="opt" :ref="`checkboxes${key}`" slot="right-icon" />
					    </van-cell>
					  </van-cell-group>
					</van-checkbox-group>
				</van-panel>
				<van-panel v-else-if="item.type == 1"  :title="item.stem" status="简答题">
					<van-field v-model="invDetails[key].answer" rows="3" autosize label="请作答" type="textarea" placeholder="请输入本题回答"/>
				</van-panel>
			</view>
		</van-panel>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				invId: 0,
				invName: "",
				invDetails: [],
				invDescription: "",
				userInfo: {},
				status: false,
				statusText: "等待审核",
				result: {resultlist:[]}
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
			
			// console.log(this.$route);
			var rp = require('request-promise');
			var options = {
			    method: 'POST',
			    uri: 'http://localhost:8080/getInvestigate',
			    form: {
			        // id: this.$route.query.invDetailsId
					id: 300000002
			    }
			};
			rp(options).then(res => {
				// 获取id对应问卷
				var investigate = JSON.parse(res)[0];
				 console.log(investigate);
				this.invId = investigate.id;
				this.invName = investigate.name;
				this.invDetails = JSON.parse(investigate.details);
				for(var i = 0; i < this.invDetails.length; i++){
					this.invDetails[i].answer = "";
					this.result.resultlist[i] = [];
				}
				console.log(this.invDetails);
				this.invDescription = investigate.description;
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
			toggle(key, num) {
				this.$refs[`checkboxes`+`${key}`][num].toggle();
				
				
				this.$forceUpdate();
				console.log(key);
				console.log(num);
				console.log(this.$refs);
				
				console.log(this.invDetails[key].result);
				
				//this.$refs.checkboxes[index].toggle();
			}
		}
	}
</script>

<style>
</style>
