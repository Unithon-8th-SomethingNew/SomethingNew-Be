[![Build Status](https://img.shields.io/badge/status-developing-orange)](https://github.com/dnd-side-project/dnd-6th-7-worry-record-service)
![License](https://img.shields.io/apm/l/vim-mode?color=yellowgreen)

<br>

<br>

<p align="center">
  <img width="350" height="350" src="https://user-images.githubusercontent.com/84304802/156708106-1bd09a55-2c63-499d-b5e1-ac08673c03dd.jpg" alt="이미지 준비중">
</p>

<center> <이미지 준비중> </center>



# 유니톤 8th - '서비스 이름'

<br>

서비스 설명

<br>



## 📃 컨벤션

### # 브랜치 관리 전략

**⚙️ git-flow**

<p align="center">
  <img src="https://user-images.githubusercontent.com/84304802/148559145-64a8029e-d220-4b80-b02f-eb45a0e07c05.png" alt="git-flow">
</p>




<br>


| 브랜치 종류  | 설명                                                         |
| ------------ | ------------------------------------------------------------ |
| Master(main) | 테스트 서버에서 테스트가 끝나고 운영서버로 배포 할 수 있는 브랜치 |
| develop      | 개발을 위한 브랜치                                           |
| feature      | 하나의 기능을 개발하기 위한 브랜치                           |
| hotfix       | 운영중인 버전에서 발생한 버그를 수정 하는 브랜치             |

- `feature` 브랜치는 하나의 기능을 개발하기 위한 브랜치입니다. 부모는 `develop`이며, 개발이 완료되면 `develop`에 merge합니다. 브랜치 이름은 보통 `feature/*`이 됩니다.
- `develop` 브랜치는 개발을 위한 브랜치입니다. 여러 `feature`들이 merge되는 장소이며, 아직 release되지 않은 기능들이 모여 있게 됩니다.
- `master` 브랜치는 실제 운영 중인 서비스의 브랜치입니다. 
- `hotfix` 브랜치는 서비스에 문제가 발생했을 때 핫픽스에 해당하는 브랜치입니다. 기능 개발(`feature`) 등과 달리 빠르게 대처해야 할 필요가 있기 때문에, `master` 브랜치에 직접 merge하는 전략을 취합니다.  `develop`과의 차이가 발생하기 때문에, 나중에 차이를 merge할 필요가 있습니다.

<br>

<br>

### # 브랜치 네이밍

**⚙️ 네이밍 패턴**

```
브랜치 종류/이슈번호-간단한 설명	
```

**Ex)** 이슈번호가 67인 '로그인 기능' 이슈를 구현하는 브랜치를 생성하는 경우, 브랜치 이름을<br> 	`feature/67-login` 로 작성한다.

<br>

<br>

### # 커밋 메시지

**⚙️ 메시지 구조**

```
Type : 제목 #이슈번호

본문
```

**Ex)** 이슈번호가 67인 이슈의 기능을 구현한 뒤 커밋을 하는 상황이라면 커밋 메시지의 제목을<br>	`feat : A기능 구현 #67` 으로 작성한다.

<br>

**⚙️ Type**

| 타입 종류 | 설명                                 |
| --------- | ------------------------------------ |
| feat      | 새로운 기능에 대한 커밋              |
| fix       | 수정에 대한 커밋                     |
| bug       | 버그에 대한 커밋                     |
| build     | 빌드 관련 파일 수정에 대한 커밋      |
| ci/cd     | 배포 커밋                            |
| docs      | 문서 수정에 대한 커밋                |
| style     | 코드 스타일 혹은 포맷 등에 관한 커밋 |
| refactor  | 코드 리팩토링에 대한 커밋            |
| test      | 테스트 코드 수정에 대한 커밋         |

<br>

<br>

## 🗺️ 아키텍처

<p align="center">
  <img src="https://user-images.githubusercontent.com/84304802/156708106-1bd09a55-2c63-499d-b5e1-ac08673c03dd.jpg" alt="아키텍처">
</p>



<br>

<br>

## 🛠️ 기술 스택

<br>

⚙️ **Language**

Java 1.8

⚙️ **Framework**

Spring boot / Spring Data Jpa

⚙️ **AWS**

RDS / SecretManager / EC2

⚙️ **DB**

MySQL

⚙️ **Test**

PostMan

<br>

<br>

## 👥 파트 및 개발 계획

### **[ 팀원 & 파트 ]**

#### 📱 프론트엔드 

- 최승호 [Github](https://github.com/tmdgh1592)
- 최현준 [Github](https://github.com/jun34724)

#### 🗄️ 백엔드 

- 최승준 [Github](https://github.com/PgmJun)
- 이성호 [Github](https://github.com/seonghoo1217)

#### 🎨 디자인

- 윤수정
- 박리현

<br>

**[ 개발 기간 ]** 2022/03/04 ~ 2022/03/06
<br>

📑**Notion**: https://eminent-leader-c5d.notion.site/8-c54ac65a9178446ca36ac59801a4c268

<br>
