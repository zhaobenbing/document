package com.paic.mobilecash.utils;


/**
 * Description 密码强度按如下规则进行计分，并根据不同的得分为密码进行安全等级划分。 
 * 一、密码长度: 5 分: 小于等于 8 个字符 10 分:
 * 9 到 13 字符 25 分: 大于等于 14 个字符 
 * 二、字母: 0 分: 没有字母 10 分: 全都是小（大）写字母搜索 20 分: 大小写混合字母
 * 三、数字: 0 分: 没有数字 10 分: 小于等于7 个数字  20 分: 大于 7 个数字 
 * 四、符号: 0 分: 没有符号 10 分: 1 个符号  25 分:  大于1 个符号 
 * 五、奖励: 2 分: 字母和数字 3 分: 字母、数字和符号 5 分: 大小写字母、数字和符号 最后的评分标准: >= 90: 非常安全
 * >= 80: 安全（Secure） >= 70: 非常强 >= 60: 强（Strong） >= 50: 一般（Average） >= 25:
 * 弱（Weak） >= 0: 非常弱 请根据输入的密码字符串，进行安全评定。 注：字母：a-z, A-Z 数字：0-9 符号包含如下：
 * !"#$%&'()*+,-./ (ASCII码：0x21~0x2F) :;<=>?@ (ASCII码：0x3A~0x40) [\]^_`
 * (ASCII码：0x5B~0x60) {|}~ (ASCII码：0x7B~0x7E) Input Param String pPasswordStr:
 * 密码，以字符串方式存放。 Return Value 根据规则评定的安全等级。
 */
public class PwdSecurityLevel {
	public static Safelevel GetPwdSecurityLevel(String pPasswordStr) {

		Safelevel safelevel = Safelevel.VERY_WEAK;

		if (pPasswordStr == null) {

			return safelevel;

		}
		// 计算分数
		int grade = 0;

		int index = 0;

		char[] pPsdChars = pPasswordStr.toCharArray();
		// 统计数字
		int numIndex = 0;
		// 统计字母符
		int sLetterIndex = 0;
		// 统计非字母符
		int lLetterIndex = 0;
		// 其它字符
		int symbolIndex = 0;

		for (char pPsdChar : pPsdChars) {

			int ascll = pPsdChar;

			/*
			 * 
			 * 数字 48-57 A-Z 65 - 90 a-z 97 - 122 !"#$%&'()*+,-./ (ASCII码：33~47)
			 * 
			 * :;<=>?@ (ASCII码：58~64) [\]^_` (ASCII码：91~96) {|}~
			 * 
			 * (ASCII码：123~126)
			 */

			if (ascll >= 48 && ascll <= 57) {

				numIndex++;

			} else if (ascll >= 65 && ascll <= 90) {

				lLetterIndex++;

			} else if (ascll >= 97 && ascll <= 122) {

				sLetterIndex++;

			} else if ((ascll >= 33 && ascll <= 47)

			|| (ascll >= 58 && ascll <= 64)

			|| (ascll >= 91 && ascll <= 96)

			|| (ascll >= 123 && ascll <= 126)) {
				symbolIndex++;
			}
		}

		/*
		 * 
		 * 一、密码长度: 0 分: 小于 8 个字符  5分：8到11 10 分: 12 到 14字符 25 分: 大于等于 13 个字符
		 */

		if (pPsdChars.length < 8) {

			index = 0;

		}else if (pPsdChars.length >= 8 && pPsdChars.length <= 11) {

			index = 5;

		} else if (pPsdChars.length >= 12 && pPsdChars.length <= 14){

			index = 10;

		} else {

			index = 25;

		}

		grade += index;

		/*
		 * 
		 * 二、字母: 0 分: 没有字母 10 分: 全都是小（大）写字母 20 分: 大小写混合字母
		 */

		if (lLetterIndex == 0 && sLetterIndex == 0) {

			index = 0;

		} else if (lLetterIndex != 0 && sLetterIndex != 0) {

			index = 20;

		} else {

			index = 10;

		}

		grade += index;

		/*
		 * 
		 * 三、数字: 0 分: 没有数字 10 分: 小于7个数字 20 分: 大于7 个数字
		 */

		if (numIndex == 0) {

			index = 0;

		} else if (numIndex <= 7) {

			index = 10;

		} else {

			index = 20;

		}

		grade += index;

		/*
		 * 
		 * 四、符号: 0 分: 没有符号 10 分: 1 个符号 25 分: 大于 1 个符号
		 */

		if (symbolIndex == 0) {

			index = 0;

		} else if (symbolIndex == 1) {

			index = 10;

		} else {

			index = 25;

		}

		grade += index;

		/*
		 * 
		 * 五、奖励: 2 分: 字母和数字 3 分: 字母、数字和符号 5 分: 大小写字母、数字和符号
		 */

		if ((sLetterIndex != 0 || lLetterIndex != 0) && numIndex != 0) {

			index = 2;

		} else if ((sLetterIndex != 0 || lLetterIndex != 0) && numIndex != 0

		&& symbolIndex != 0) {

			index = 3;

		} else if (sLetterIndex != 0 && lLetterIndex != 0 && numIndex != 0

		&& symbolIndex != 0) {

			index = 5;

		}else{
			index = 2;
		}

		grade += index;

		/*
		 * 
		 * 最后的评分标准: >= 90: 非常安全 >= 80: 安全（Secure） >= 70: 非常强 >= 60: 强（Strong） >=
		 * 
		 * 50: 一般（Average） >= 25: 弱（Weak） >= 0: 非常弱
		 */

		if (grade >= 90) {

			safelevel = Safelevel.VERY_SECURE;

		} else if (grade >= 80) {

			safelevel = Safelevel.SECURE;

		} else if (grade >= 70) {

			safelevel = Safelevel.VERY_STRONG;

		} else if (grade >= 60) {

			safelevel = Safelevel.STRONG;

		} else if (grade >= 50) {

			safelevel = Safelevel.AVERAGE;

		} else if (grade >= 25) {

			safelevel = Safelevel.WEAK;

		} else if (grade >= 0) {

			safelevel = Safelevel.VERY_WEAK;

		}
		return safelevel;

	}

	public enum Safelevel {

		VERY_WEAK, /* 非常弱 */

		WEAK, /* 弱 */

		AVERAGE, /* 一般 */

		STRONG, /* 强 */

		VERY_STRONG, /* 非常强 */

		SECURE, /* 安全 */

		VERY_SECURE /* 非常安全 */

	}
}
