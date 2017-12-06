# DataRootUniversity-Test5
DataRoot University deployment test

TASK 2 is not straightforward. Here's how to do it:
1. cd Ansible directory
2. run ./build-machines.sh (if not executable - chmod +x it)
3. run ./run-slave.sh (IMPORTANT TO RUN IN BEFORE HOST)	
4. run ./run-host.sh
5. You should be root@host now.
6. Generate ssh keys: ssh-keygen -t rsa (smash that ENTER buton)
7. ssh-copy-id -i ~/.ssh/id_rsa root@remote-server (remote-server root password is alpine)
8. You should ssh freely now.
9. cd /etc/ansible
9. ansible-playbook -i ./hosts /playbooks/main.yml