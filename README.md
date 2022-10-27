# Introduction

Technical assessment for Capital One Software Engineering, Full-Time 2023 position.

Participant name: [Xiaoqi Gao](https://drive.google.com/file/d/12F37lIn_6t7Qv3dn-f5hZFQw8FCC6Pyf/view?usp=sharing)

*Note: This is a console app.
For the web application version, where I used Spring Boot, MVC, MySQL, JUnit, please see the other email attachment.
## How to run?

Inside `src/transactionList.txt`, please enter the transaction list information in the following format:
```bash
transaction-name, date, merchant-code, amount-cents
```
For instance,
```bash
T01, 2021-05-01, sportcheck, 21000
```
Then, run `CreditCardRewardsApp` 

## About Reward Rules

After calculation, I found that CompositeRule 3 and CompositeRule 5 are less cost-efficient than a combination of CompositeRule 6 and CompositeRule 7.
Therefore, I did not consider them when calculating the maximum monthly reward point

### Why CompositeRule 3?
CompositeRule 3: 200 points for every $75 spend at Sport Check

which can be substituted by 3 * CompositeRule 6 + 15 * CompositeRule 7 = 3 * 75 + 15 = 240 points 

### Why CompositeRule 5?
CompositeRule 5: 75 points for every $25 spend at Sport Check, $10 spend at Tim Hortons

which can be substituted by 1 * CompositeRule 6 + 15 * CompositeRule 7 = 75 + 15 = 90 points

## Finally
Thank you for taking the time reviewing my submission!

