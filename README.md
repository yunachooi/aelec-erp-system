# Aelec ERP System

A전자 전사적 자원 관리(ERP) 시스템 개발 프로젝트 - 자재 관리 모듈  
(Spring Framework 기반, 추후 웹 UI 및 기능 확장 예정)<br>
**프로젝트 기간 : 2025년 04월 18일 ~ 04월 22일**

---

## 📌 프로젝트 개요

**Aelec ERP System**은 A전자 사내의 자재 관리를 효율적으로 수행하기 위한 ERP 시스템입니다. 본 프로젝트는 Spring Framework와 SQL을 기반으로 하며, 향후 웹 기반 UI와 추가 기능을 통해 확장될 예정입니다.

---

## 🛠️ 기술 스택

- **백엔드**: Java 17, Spring Framework (Spring Boot, Spring MVC, Spring Data JPA)
- **데이터베이스**: OracleDB
- **버전 관리**: GitHub

---

## 🔧 주요 기능

- 자재 등록, 조회, 수정, 삭제 기능
- 재고 수량 파악 및 입출고 관리
- 공급업체 및 고객사 정보 관리
- 자재 분류 및 단위 정보 관리

---

## 📌 ERD
![Image](https://github.com/user-attachments/assets/703d875c-fe1f-4f23-8aab-8b05793f72ab)

---

## 👥 팀 구성

- **팀원**
  - **문성식**: 
  - **최유나**: 
---

## 🚀 향후 계획

- JSP를 활용한 사용자 인터페이스 구현
- 자재 입출고 및 재고 현황 보고서 생성
- 재고 부족 시 알림 기능 구현
- 관리자 페이지 구현

---

## 📁 프로젝트 구조
```text
aelec-erp-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── aelec/
│   │   │           └── erp/
│   │   │               ├── controller/     # 요청 처리 로직
│   │   │               ├── service/        # 비즈니스 로직
│   │   │               ├── repository/     # DB 접근 로직
│   │   │               └── model/          # 엔티티 및 DTO
│   │   └── resources/
│   │       ├── application.properties      # 환경 설정
│   │       └── static/                     # 정적 파일 (예정)
│   └── test/
│       └── java/
├── build.gradle                             # 빌드 설정
└── README.md                                # 프로젝트 설명서
