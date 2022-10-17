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

After calculation, I found that Rule 3 and Rule 5 are less cost-efficient than a combination of Rule 6 and Rule 7.
Therefore, I did not consider them when calculating the maximum monthly reward point

### Why Rule 3?
Rule 3: 200 points for every $75 spend at Sport Check

which can be substituted by 3 * Rule 6 + 15 * Rule 7 = 3 * 75 + 15 = 240 points 

### Why Rule 5?
Rule 5: 75 points for every $25 spend at Sport Check, $10 spend at Tim Hortons

which can be substituted by 1 * Rule 6 + 15 * Rule 7 = 75 + 15 = 90 points

## Finally
Thank you for taking the time reviewing my submission!

