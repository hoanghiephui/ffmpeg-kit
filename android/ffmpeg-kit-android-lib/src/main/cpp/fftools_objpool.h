#ifndef FFTOOLS_OBJPOOL_H
#define FFTOOLS_OBJPOOL_H

typedef struct ObjPool ObjPool;

typedef void* (*ObjPoolCBAlloc)(void);
typedef void  (*ObjPoolCBReset)(void *);
typedef void  (*ObjPoolCBFree)(void **);

void     objpool_free(ObjPool **op);
ObjPool *objpool_alloc(ObjPoolCBAlloc cb_alloc, ObjPoolCBReset cb_reset,
                       ObjPoolCBFree cb_free);
ObjPool *objpool_alloc_packets(void);
ObjPool *objpool_alloc_frames(void);

int  objpool_get(ObjPool *op, void **obj);
void objpool_release(ObjPool *op, void **obj);

#endif // FFTOOLS_OBJPOOL_H
