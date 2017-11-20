create sequence hibernate_sequence start with 1 increment by 1
create table app_user (id bigint not null, account_non_expired boolean, account_non_locked boolean, credentials_non_expired boolean, email varchar(255), enabled boolean, first_name varchar(255), last_name varchar(255), password varchar(255), sign_in_provider integer, username varchar(255), primary key (id))
create table campaign (id bigint generated by default as identity, created_at timestamp, details varchar(255), goal varchar(255), image varchar(255), name varchar(255), reason varchar(255), updated_at timestamp, video varchar(255), primary key (id))
create table payment_request (id bigint generated by default as identity, callback_url varchar(255), channel_id varchar(255), checksum_hash varchar(255), created_at timestamp, customer_id varchar(255), email_id varchar(255), industry_type_id varchar(255), mid varchar(255), mobile_no varchar(255), order_details varchar(255), order_id binary, txn_amount decimal(19,2), updated_at timestamp, website varchar(255), campaign_id bigint, primary key (id))
create table payment_response (id bigint generated by default as identity, bank_name varchar(255), bank_transaction_id varchar(255), checksum_hash varchar(255), created_at timestamp, currency varchar(255), customer_id varchar(255), email_id varchar(255), gate_way_name varchar(255), is_validchecksum_hash boolean, mobile_no varchar(255), order_details varchar(255), order_id binary, payment_mode varchar(255), response_code varchar(255), response_message varchar(255), status varchar(255), transaction_date timestamp, transaction_id varchar(255), transaction_type varchar(255), txn_amount decimal(19,2), updated_at timestamp, campaign_id bigint, payment_request_id bigint, primary key (id))
create table token_black_list (jti varchar(255) not null, expires bigint, is_black_listed boolean, user_id bigint, primary key (jti))
create table user_roles (user_id bigint not null, roles varchar(255))
alter table app_user add constraint UK_3k4cplvh82srueuttfkwnylq0 unique (username)
alter table payment_request add constraint FKaag4s8xfvbt4ub7ydv5bfjv0a foreign key (campaign_id) references campaign
alter table payment_response add constraint FK5o3xf5ykkq0d1grpbtvr537ng foreign key (campaign_id) references campaign
alter table payment_response add constraint FKh82klegnadklco9osk7slv909 foreign key (payment_request_id) references payment_request
alter table user_roles add constraint FK6fql8djp64yp4q9b3qeyhr82b foreign key (user_id) references app_user
