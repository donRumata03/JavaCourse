initial_number = 5740487520438743858
bin_str = bin(initial_number)[2:]

head = bin_str[:32]
tail = bin_str[32:]

real_tail = list(tail)
for i in range(len(tail)):
    b = int(tail[i]) ^ int(head[i])
    real_tail[i] = b

ans_bin_repr = head + "".join(map(str, real_tail))
print(ans_bin_repr, int(ans_bin_repr, 2))
